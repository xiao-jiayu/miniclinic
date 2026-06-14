package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.edu.fju.miniclinic.model.AppointmentRepository;
import tw.edu.fju.miniclinic.model.DoctorRepository;
import tw.edu.fju.miniclinic.model.PatientRepository;

import java.util.Collection;
//import java.util.List;
//import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StatsController {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @GetMapping("/stats")
    public String getStats(Model model) {
        model.addAttribute("totalDoctors", doctorRepo.count());
        model.addAttribute("totalPatients", patientRepo.count());
        model.addAttribute("totalAppointments", appointmentRepo.count());
        model.addAttribute("departmentStats", ((Collection<Object[]>) appointmentRepo.countAppointmentsByDepartment()).stream().collect(Collectors.toMap(obj -> (String) obj[0], obj -> (Long) obj[1])));
        return "stats";
    }
}