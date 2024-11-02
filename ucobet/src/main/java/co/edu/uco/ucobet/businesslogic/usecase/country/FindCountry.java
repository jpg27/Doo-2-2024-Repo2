package co.edu.uco.ucobet.businesslogic.usecase.country;

import java.util.List;

import co.edu.uco.ucobet.businesslogic.usecase.UseWithReturn;
import co.edu.uco.ucobet.domain.CountryDomain;

public interface FindCountry extends UseWithReturn<CountryDomain, List<CountryDomain>> {

}
