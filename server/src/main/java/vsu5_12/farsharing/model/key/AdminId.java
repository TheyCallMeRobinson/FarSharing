package vsu5_12.farsharing.model.key;

import lombok.*;
import vsu5_12.farsharing.model.entity.UserEntity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminId implements Serializable {

    private static final long serialVersionUID = 7324976624471403761L;

    @OneToOne
    @MapsId("uid")
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private UserEntity user;
}
