package fashionartkids.mgmt.model.form;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TalentForm {
    @NotBlank
    @Size(min=2)
    private String firstName;

    @NotBlank
    @Size(min=2)
    private String lastName;

    @NotNull
    private String gender;

    @NotNull
    @Past
    private LocalDate birthDate;
    private String street;
    private String streetNumber;
    @Size(min=2)
    private String zip;
    private String city;
    private String country;
    @Email
    private String email;
    private String phone;
    private String mobile;
    private Integer height;
    private Integer weight;
    private Integer clothingSize;
    private Integer shoeSize;
    private String eyeColor;
}
