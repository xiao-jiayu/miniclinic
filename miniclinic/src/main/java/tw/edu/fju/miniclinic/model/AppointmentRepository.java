package tw.edu.fju.miniclinic.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByApptDate(LocalDate apptDate);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByPatient(Patient patient);
    long countByApptDateBetween(LocalDate from, LocalDate to);
    List<Appointment> findByDoctorAndApptDate(Doctor doctor, LocalDate apptDate);

        // 查詢各科別的掛號數量
        @Query("SELECT d.department, COUNT(a) FROM Appointment a JOIN a.doctor d GROUP BY d.department ORDER BY d.department")
        List<Object[]> countAppointmentsByDepartment();
        List<Appointment> findByApptDateAndDoctor(LocalDate apptDate, Doctor doctor);
}