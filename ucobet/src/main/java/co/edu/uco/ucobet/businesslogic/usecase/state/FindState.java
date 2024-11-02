package co.edu.uco.ucobet.businesslogic.usecase.state;

import java.util.List;

import co.edu.uco.ucobet.businesslogic.usecase.UseWithReturn;
import co.edu.uco.ucobet.domain.StateDomain;

public interface FindState extends UseWithReturn<StateDomain, List<StateDomain>> {

}
