package bridge.contracts;

import bridge.decorators.LimitedRoadDecorator;
import bridge.services.LimitedRoadService;

public class LimitedRoadContract extends /* include */ LimitedRoadDecorator {

	int limit;
	
	public LimitedRoadContract(LimitedRoadService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// remarque : include et non refine donc on n'hérite
		// pas des invariants de RoadSectionService, il faut refaire des tests.
				
		/* A COMPLETER */
		// inv : isFull() == (getNbCars() == getLimit())
		// inv : getNbCars() <= getLimit()
		if(isFull() == (getNbCars() != getLimit())) {
			Contractor.defaultContractor().invariantError("LimitedRoadContract","Invariant full");
			return;
		}
		
		if(getNbCars() > getLimit()) {
			Contractor.defaultContractor().invariantError("LimitedRoadContract","Invariant > Limite");
			return;
		}
	}
	
	/** Initialisation
	 * pre: lim>0
	 * post: getLimit() == lim
	 */
	public void init(int lim) {
		
		if (getLimit() > 0) {
			Contractor.defaultContractor().preconditionError("LimitedRoadContract", "init", "getLimit() > 0");
			return ;
		}
		
		getDelegate().init();
		checkInvariant();
		
		if (getLimit() != lim) {
			Contractor.defaultContractor().postconditionError("LimitedRoadContract", "init", "getLimit() != 0");
			return ;
		}
		if (getNbCars() != 0) {
			Contractor.defaultContractor().postconditionError("LimitedRoadContract", "init", "getNbCars() != 0");
			return ;
		}
	}
	
	/** Observateur : pont plein ? */
	public boolean isFull() {
		if(getLimit() >= getNbCars()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Redéfinition de l'entrée
	 * pre: !isFull()
	 * Remarque : on n'a pas  true ==> !iFull()  donc LimitedRoadService ne raffine pas RoadSectionService
	 */
	public void enter() {
		checkInvariant();
		if(isFull()) {
			Contractor.defaultContractor().preconditionError("LimitedRoadContract", "enter", "!isFull()");
			return ;
		}
		super.enter();
	}
}
