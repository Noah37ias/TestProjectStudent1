package controller;

import ordination.*;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NullMarked
public abstract class Controller {
    private static Storage storage = new Storage();

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    /**
     * Opret og returner en PN ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException.
     * Pre: antal > 0.
     */
    public static PN opretPNOrdination(
            LocalDate startDato, LocalDate slutDato, double antal,
            Patient patient, @Nullable Lægemiddel lægemiddel
    ) {
        // TODO
        return new PN();
    }

    /**
     * Opret og returner en DagligFast ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException.
     * Pre: morgenAntal, middagAntal, aftenAntal, natAntal er alle >= 0.
     */
    public static DagligFast opretDagligFastOrdination(
            LocalDate startDato, LocalDate slutDato,
            double morgenAntal, double middagAntal, double aftenAntal, double natAntal,
            Patient patient, @Nullable Lægemiddel lægemiddel
    ) {
        if (startDato.isAfter(slutDato)) {
            throw new IllegalArgumentException("Start dato er efter slut dato dette er ikke muligt");
        } else {
            DagligFast d = new DagligFast(startDato, slutDato, morgenAntal, middagAntal, aftenAntal, natAntal, lægemiddel);
            patient.addOrdinationer(d);
            return d;
        }
    }

    /**
     * Opret og returner en DagligSkæv ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException.
     * Hvis antallet af elementer i klokkeSlet og antalEnheder er forskellige,
     * kastes en IllegalArgumentException.
     * Pre: I antalEnheder er alle tal >= 0.
     */
    public static DagligSkæv opretDagligSkævOrdination(
            LocalDate startDen, LocalDate slutDen, LocalTime[] klokkeSlet, double[] antalEnheder,
            Patient patient, @Nullable Lægemiddel lægemiddel
    ) {
        if (startDen.isAfter(slutDen)) {
            throw new IllegalArgumentException("StartDato er efter slutDato");
        } else if (klokkeSlet.length != antalEnheder.length) {
            throw new IllegalArgumentException("Antallet af elementer i klokkeSlet og antalEnheder er forskellige");
        } else {
            DagligSkæv dagligSkæv = new DagligSkæv(startDen, slutDen, lægemiddel, klokkeSlet, antalEnheder);
            patient.addOrdinationer(dagligSkæv);
            return dagligSkæv;
        }
    }

    /**
     * Tilføj en dato for anvendelse af PN ordinationen.
     * Hvis datoen ikke er indenfor ordinationens gyldighedsperiode,
     * kastes en IllegalArgumentException.
     */
    public static void anvendOrdinationPN(PN ordination, LocalDate dato) {
        if (!ordination.anvendDosis(dato)) {
            throw new IllegalArgumentException("Datoen er uden for ordinationens gyldighedsperiode");
        }
    }

    /**
     * Returner den anbefalede dosis pr. døgn til patienten af lægemidlet.
     * (Den anbefalede dosis afhænger af patientens vægt.)
     */
    public static double anbefaletDosisPrDøgn(Patient patient, Lægemiddel lægemiddel) {
        double vægt = patient.getVægt();
        double dosis = 0;
        if (vægt > 120) {
            dosis = lægemiddel.getAntalPrKgPrDøgnTung();
        } else if (vægt >= 25) {
            dosis = lægemiddel.getAntalPrKgPrDøgnNormal();
        } else if (vægt < 25) {
            dosis = lægemiddel.getAntalPrKgPrDøgnLet();
        }
        return vægt * dosis;
    }

    /**
     * Returner antal ordinationer af lægemidlet for patienter med vægt i vægtintervallet.
     */
    public static int antalOrdinationerPrVægtPrLægemiddel(
            double vægtStart, double vægtSlut, Lægemiddel lægemiddel
    ) {
        int antal = 0;
        for (Patient p : getAllePatienter()) {
            if (p.getVægt() <= vægtSlut || p.getVægt() >= vægtStart) {
                for (Ordination o : p.getOrdinationer()) {
                    if (o.getLægemiddel().equals(lægemiddel)) {
                        antal++;
                    }
                }
            }
        }
        return antal;
    }

    public static List<Patient> getAllePatienter() {
        return storage.getAllePatienter();
    }

    public static List<Lægemiddel> getAlleLægemidler() {
        return storage.getAlleLægemidler();
    }

    public static Patient opretPatient(String cpr, String navn, double vægt) {
        Patient p = new Patient(cpr, navn, vægt);
        storage.storePatient(p);
        return p;
    }

    public static Lægemiddel opretLægemiddel(
            String navn, String enhed,
            double enhedPrKgPrDøgnLet, double enhedPrKgPrDøgnNormal, double enhedPrKgPrDøgnTung
    ) {
        Lægemiddel lm = new Lægemiddel(
                navn, enhed,
                enhedPrKgPrDøgnLet, enhedPrKgPrDøgnNormal, enhedPrKgPrDøgnTung
        );
        storage.storeLægemiddel(lm);
        return lm;
    }
}
