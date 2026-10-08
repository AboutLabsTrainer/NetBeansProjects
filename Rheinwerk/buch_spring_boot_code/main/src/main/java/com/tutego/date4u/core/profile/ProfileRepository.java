package com.tutego.date4u.core.profile;

import com.querydsl.core.types.dsl.NumberPath;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends
    JpaRepository<Profile, Long>,
    QuerydslPredicateExecutor<Profile>,
    QuerydslBinderCustomizer<QProfile> {

  @Query( nativeQuery = true, value = """
      SELECT YEAR(lastseen) AS y, MONTH(lastseen) AS m, COUNT(*) AS COUNT
      FROM profile
      WHERE lastseen > ?1 AND lastseen < ?2
      GROUP BY YEAR(lastseen), MONTH(lastseen)""" )
  List<Tuple> findMonthlyProfileCount( LocalDate startDate, LocalDate endDate );

  @Override
  default void customize( QuerydslBindings bindings, QProfile profile ) {
    MultiValueBinding<NumberPath<Short>, Short> bindingManelengthBetween = ( path, value ) -> {
      List<? extends Number> values = new ArrayList<>( value );
      short minManelength = values.get( 0 ).shortValue();
      if ( values.size() == 1 )
        return Optional.of( path.goe( minManelength ) );
      else {
        short maxManelength = values.get( 1 ).shortValue();
        return Optional.of( path.between( minManelength, maxManelength ) );
      }
    };
    bindings.bind( profile.manelength ).all( bindingManelengthBetween );
  }
}