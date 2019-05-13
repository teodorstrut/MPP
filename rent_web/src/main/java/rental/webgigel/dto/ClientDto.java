package rental.webgigel.dto;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto {
    private String name;
    private String address;
}
