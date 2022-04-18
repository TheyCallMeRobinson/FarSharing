package vsu5_12.farsharing.model.entity;

import lombok.*;
import vsu5_12.farsharing.model.key.AdminId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admin")
public class AdminEntity {

    @EmbeddedId
    private AdminId uid;
}
