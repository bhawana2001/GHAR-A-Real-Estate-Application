package com.ghar.ghar.service;

import com.ghar.ghar.entity.Property;
import com.ghar.ghar.exception.CustomException;
import com.ghar.ghar.repository.PropertyRepository;
import com.ghar.ghar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        if(propertyRepository.existsById(id)){
           return propertyRepository.findById(id).get();
        }else{
            throw new CustomException("Property not found with ID: "+id);
        }
    }

    public Property addProperty(Property property) {
        if(userRepository.existsById(property.getOwner().getId())) {
            return propertyRepository.save(property);
        }
        else {
            throw new CustomException("Owner details are invalid with ID: "+property.getOwner().getId());
        }
    }

    public Property updateProperty(Long id, Property property) {
        if(userRepository.existsById(property.getOwner().getId())) {
            if (propertyRepository.existsById(id)) {
                Property existingProperty = propertyRepository.findById(id).get();
                existingProperty.setAddress(property.getAddress());
                existingProperty.setPropertyType(property.getPropertyType());
                existingProperty.setCity(property.getCity());
                existingProperty.setBedrooms(property.getBedrooms());
                existingProperty.setAmenities(property.getAmenities());
                existingProperty.setBathrooms(property.getBathrooms());
                existingProperty.setDateAdded(property.getDateAdded());
                existingProperty.setDescription(property.getDescription());
                existingProperty.setImages(property.getImages());
                existingProperty.setOwner(property.getOwner());
                existingProperty.setPrice(property.getPrice());
                existingProperty.setSize(property.getSize());
                existingProperty.setState(property.getState());
                existingProperty.setStatus(property.getStatus());
                existingProperty.setZipCode(property.getZipCode());
                existingProperty.setTitle(property.getTitle());
                return propertyRepository.save(existingProperty);
            } else {
                throw new CustomException("Property not found with ID: " + id);
            }
        }else{
            throw new CustomException("Owner details are invalid with ID: "+property.getOwner().getId());
        }
    }

    public void deleteProperty(Long id) {
        if(propertyRepository.existsById(id)){
            propertyRepository.deleteById(id);
        }else{
            throw new CustomException("Property not found with ID: "+id);
        }
    }
}
