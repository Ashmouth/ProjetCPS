package bridge.contracts;

import bridge.services.BridgeService;

public class BridgeContract extends LimitedRoadContract implements BridgeService {

	public BridgeContract(BridgeService delegate) {
		super(delegate);
	}

	@Override
	protected BridgeService getDelegate() {
		return (BridgeService) super.getDelegate();
	}
	
	@Override
	public int getNbIn() {
		return getDelegate().getNbIn();
	}

	@Override
	public int getNbOut() {
		return getDelegate().getNbOut();
	}
	
	public void checkInvariant() {
		// TODO
		// raffinement donc
		super.checkInvariant();
	}
	

	@Override
	public void init() {
		// TODO
		getDelegate().init();
	}

	@Override
	public void init(int lim) {
		// TODO
		getDelegate().init(lim);
	}

	@Override
	public void enterIn() {
		// TODO
		getDelegate().enterIn();
	}

	@Override
	public void leaveIn() {
		// TODO
		getDelegate().leaveIn();
	}

	@Override
	public void enterOut() {
		// TODO
		getDelegate().enterOut();
	}

	@Override
	public void leaveOut() {
		// TODO
		getDelegate().leaveOut();
	}
	
}
