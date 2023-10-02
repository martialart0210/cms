package com.mac.martial_arts_cms.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;



/**
 * The Class Inventory.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "INVENTORY")
@Table(name = "inventorys")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 7629287212050058050L;
    @Id
    @Column(name = "INVENTORY_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

}
