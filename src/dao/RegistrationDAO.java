package dao;

import java.util.ArrayList;
import model.Registration;

public class RegistrationDAO {

    private ArrayList<Registration> registrations = new ArrayList<>();

    public void save(Registration registration) {
        registrations.add(registration);
    }

    public ArrayList<Registration> getAll() {
        return registrations;
    }

    public Registration findById(String id) {

        for (Registration r : registrations) {

            if (r.getRegistrationID().equals(id)) {
                return r;
            }
        }

        return null;
    }

    public boolean delete(String id) {

        Registration registration = findById(id);

        if (registration != null) {
            registrations.remove(registration);
            return true;
        }

        return false;
    }

    public boolean update(Registration updatedRegistration) {

        for (int i = 0; i < registrations.size(); i++) {

            if (registrations.get(i).getRegistrationID()
                    .equals(updatedRegistration.getRegistrationID())) {

                registrations.set(i, updatedRegistration);
                return true;
            }
        }

        return false;
    }
}