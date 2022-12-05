package com.rw.test.tastefulapp.repository.entity;

import com.rw.test.tastefulapp.model.DietType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(name = "dietTypeFilter", parameters = {@ParamDef(name = "dietType", type = String.class)})
@FilterDef(name = "servingsFilter", parameters = {@ParamDef(name = "servings", type = Integer.class)})
@Filter(name = "dietTypeFilter", condition = "diet_type = :dietType")
@Filter(name = "servingsFilter", condition = "servings >= :servings")
public class CookingRecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private DietType dietType;
    private int servings;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Set<CookingIngredientEntity> ingredients;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<CookingInstructionEntity> cookingInstructions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CookingRecipeEntity that = (CookingRecipeEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public CookingRecipeEntity initializeCollections() {
        Hibernate.initialize(ingredients);
        Hibernate.initialize(cookingInstructions);
        return this;
    }
}
