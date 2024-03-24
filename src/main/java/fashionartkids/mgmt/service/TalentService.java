package fashionartkids.mgmt.service;

import fashionartkids.mgmt.entity.AddressEntity;
import fashionartkids.mgmt.entity.ContactEntity;
import fashionartkids.mgmt.entity.TalentEntity;
import fashionartkids.mgmt.model.form.TalentForm;
import fashionartkids.mgmt.model.talent.*;
import fashionartkids.mgmt.repository.TalentRepository;
import fashionartkids.mgmt.repository.TalentRepository2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TalentService {

    @Autowired
    private TalentRepository talentRepository;
    @Autowired
    private TalentRepository2 talentRepository2;

    @GetMapping("/models/fetch")
    public DataTablesOutput<Talent> fetchAll(DataTablesInput input) {
        return talentRepository2.findAll(input, this::mapTalent);
    }

    public fashionartkids.mgmt.model.talent.Talent fetch(Integer id) throws ChangeSetPersister.NotFoundException {
        return mapTalent(talentRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    public String add(Talent formData) {
        try {
            talentRepository.save(mapEntity(formData));
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    public Talent update(Integer id, Talent formData) throws ChangeSetPersister.NotFoundException {
        return mapTalent(talentRepository.save(mapEntity(formData, talentRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new))));
    }

    public String delete(Integer id) {
        try {
            if (talentRepository.existsById(id)) {
                talentRepository.deleteById(id);
                return "Deletion successful";
            }
        } catch (Exception e) {
            return "Deletion failed.".concat(e.getMessage());
        }
        return "hio";
    }


    private Talent mapTalent(TalentEntity entity) {
        Talent talent = new Talent();
        talent.setId(entity.id());
        talent.setModelNumber(generateIdentifier(entity.id().toString(), entity.birthDate()));
        talent.setFirstName(entity.firstName());
        talent.setLastName(entity.lastName());
        talent.setGender(Gender.valueOf(entity.gender()));
        talent.setBirthDate(entity.birthDate());
        talent.setHeight(entity.height());
        talent.setWeight(entity.weight());
        talent.setClothingSize(entity.clothingSize());
        talent.setShoeSize(entity.shoeSize());
        talent.setEyeColor(EyeColor.valueOf(entity.eyeColor()));
        talent.setImages(entity.images());
        talent.setJobs(entity.jobs());

        Address address = new Address();
        address.setStreet(entity.address().street());
        address.setStreetNumber(entity.address().streetNumber());
        address.setZip(entity.address().zip());
        address.setCity(entity.address().city());
        address.setCountry(entity.address().country());
        talent.setAddress(address);

        Contact contact = new Contact();
        contact.setEmail(entity.contact().email());
        contact.setPhone(entity.contact().phone());
        contact.setMobile(entity.contact().mobile());
        talent.setContact(contact);

        return talent;
    }

    private TalentEntity mapEntity(Talent formData) {
        return new TalentEntity()
                .firstName(formData.getFirstName())
                .lastName(formData.getLastName())
                .gender(formData.getGender().toString())
                .birthDate(formData.getBirthDate())
                .address(new AddressEntity().street(formData.getAddress().getStreet()).streetNumber(formData.getAddress().getStreetNumber()).zip(formData.getAddress().getZip()).city(formData.getAddress().getCity()).country(formData.getAddress().getCountry()))
                .contact(new ContactEntity().email(formData.getContact().getEmail()).phone(formData.getContact().getPhone()).mobile(formData.getContact().getMobile()))
                .height(formData.getHeight())
                .weight(formData.getWeight())
                .clothingSize(formData.getClothingSize())
                .shoeSize(formData.getShoeSize())
                .eyeColor(formData.getEyeColor().toString());
    }

    private TalentEntity mapEntity(Talent formData, TalentEntity entity) {
        return entity
                .firstName(formData.getFirstName())
                .lastName(formData.getLastName())
                .gender(formData.getGender().toString())
                .birthDate(formData.getBirthDate())
                .address(new AddressEntity().street(formData.getAddress().getStreet()).streetNumber(formData.getAddress().getStreetNumber()).zip(formData.getAddress().getZip()).city(formData.getAddress().getCity()).country(formData.getAddress().getCountry()))
                .contact(new ContactEntity().email(formData.getContact().getEmail()).phone(formData.getContact().getPhone()).mobile(formData.getContact().getMobile()))
                .height(formData.getHeight())
                .weight(formData.getWeight())
                .clothingSize(formData.getClothingSize())
                .shoeSize(formData.getShoeSize())
                .eyeColor(formData.getEyeColor().toString());
    }

    private String generateIdentifier(String id, LocalDate birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        String year = birthDate.format(formatter);
        return year.concat("-").concat(StringUtils.leftPad(id, 4, "0"));
    }
}
