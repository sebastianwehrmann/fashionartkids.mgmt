package fashionartkids.mgmt.service;

import fashionartkids.mgmt.entity.TalentEntity;
import fashionartkids.mgmt.model.form.TalentForm;
import fashionartkids.mgmt.model.talent.*;
import fashionartkids.mgmt.repository.TalentRepository;
import fashionartkids.mgmt.repository.TalentRepository2;
import jakarta.validation.Valid;
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
import java.util.stream.Stream;

@Service
public class TalentService {

    @Autowired
    private TalentRepository talentRepository;
    @Autowired
    private TalentRepository2 talentRepository2;

    @GetMapping("/models/fetch")
    public DataTablesOutput<Talent> fetchAllNew(DataTablesInput input) {
        DataTablesOutput<Talent> all = talentRepository2.findAll(input, this::mapTalent);
        return talentRepository2.findAll(input, this::mapTalent);
    }
    public Page<Talent> fetchAll(String keyword, int page, int size, String sortField, String sortDirection) {
        Direction direction = sortDirection.equals("desc") ? Direction.DESC : Direction.ASC;
        Order order = new Order(direction, sortField);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));
        Page<Talent> talents;
        if (keyword == null) {
            talents = talentRepository.findAll(pageable).map(this::mapTalent);
        } else {
            talents = talentRepository.findByFirstNameContainingIgnoreCase(keyword, pageable).map(this::mapTalent);
        }
        return talents;
    }

    public Talent fetch(Integer id) throws ChangeSetPersister.NotFoundException {
        Optional<TalentEntity> talentEntity = talentRepository.findById(id);
        return mapTalent(talentRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    public String add(TalentForm formData) {
        try {
            talentRepository.save(mapEntity(formData));
        } catch (Exception e) {

        }
        return "success";
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
        talent.setBirthDate(entity.birthDate().format(DateTimeFormatter.ofPattern("d.M.y")));
        talent.setHeight(entity.height());
        talent.setWeight(entity.weight());
        talent.setClothingSize(entity.clothingSize());
        talent.setShoeSize(entity.shoeSize());
        talent.setEyeColor(EyeColor.valueOf(entity.eyeColor()));
        talent.setImages(entity.images());
        talent.setJobs(entity.jobs());

        Address address = new Address();
        address.setStreet(entity.street());
        address.setStreetNumber(entity.streetNumber());
        address.setZip(entity.zip());
        address.setCity(entity.city());
        address.setCountry(entity.country());
        talent.setAddress(address);

        Contact contact = new Contact();
        contact.setEmail(entity.email());
        contact.setPhone(entity.phone());
        contact.setMobile(entity.mobile());
        talent.setContact(contact);

        return talent;
    }

    private TalentEntity mapEntity(TalentForm formData) {
        return new TalentEntity()
                .firstName(formData.getFirstName())
                .lastName(formData.getLastName())
                .gender(formData.getGender())
                .birthDate(formData.getBirthDate())
                .street(formData.getStreet())
                .streetNumber(formData.getStreetNumber())
                .zip(formData.getZip())
                .city(formData.getCity())
                .country(formData.getCountry())
                .email(formData.getEmail())
                .phone(formData.getPhone())
                .mobile(formData.getMobile())
                .height(formData.getHeight())
                .weight(formData.getWeight())
                .clothingSize(formData.getClothingSize())
                .shoeSize(formData.getShoeSize())
                .eyeColor(formData.getEyeColor());
    }

    private String generateIdentifier(String id, LocalDate birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        String year = birthDate.format(formatter);
        return year.concat("-").concat(StringUtils.leftPad(id, 4, "0"));
    }
}
