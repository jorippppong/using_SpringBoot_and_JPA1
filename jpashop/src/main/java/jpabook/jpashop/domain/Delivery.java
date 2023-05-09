package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  //ordinal 절대 쓰면 안됨.(상수로 지정해서 중간에 값 추가하면 다 밀려서 망함, 꼭 STRING 으로 하지 )
    private DeliveryStatus status;  //READY, COMP
}
