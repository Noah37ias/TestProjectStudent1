package storage;

import java.util.ArrayList;
import java.util.List;

import ordination.Lægemiddel;
import ordination.Patient;

public class Storage {
    private final List<Patient> patienter = new ArrayList<>();
    private final List<Lægemiddel> lægemidler = new ArrayList<>();

    public List<Patient> getAllPatienter() {
        return List.copyOf(patienter);
    }

    public void storePatient(Patient patient) {
        patienter.add(patient);
    }

    public List<Lægemiddel> getAllLægemidler() {
        return List.copyOf(lægemidler);
    }

    public void storeLægemiddel(Lægemiddel lægemiddel) {
        lægemidler.add(lægemiddel);
    }
}
