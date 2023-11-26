package com.ghar.ghar.service;

import com.ghar.ghar.entity.Appointment;
import com.ghar.ghar.exception.CustomException;
import com.ghar.ghar.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Appointment not found with id: " + id));
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Appointment not found with id: " + id));
        Appointment updatedAppointments = updateEntity(existingAppointment, updatedAppointment);
        return appointmentRepository.save(updatedAppointments);
    }

    public void deleteAppointment(Long id) {
        if(appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        }else{
            throw new CustomException("Appointment not found with id: " + id);
        }
    }

    private Appointment updateEntity(Appointment existingAppointment, Appointment updatedAppointment) {
        if (existingAppointment == null || updatedAppointment == null) {
            return null;
        }

        existingAppointment.setProperty(updatedAppointment.getProperty());
        existingAppointment.setUser(updatedAppointment.getUser());
        existingAppointment.setDateTime(updatedAppointment.getDateTime());
        existingAppointment.setStatus(updatedAppointment.getStatus());
        existingAppointment.setNotes(updatedAppointment.getNotes());

        return existingAppointment;
    }
}
