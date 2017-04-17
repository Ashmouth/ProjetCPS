package streetfighter.services;


public interface CharacterService {

	//Observators: 
	public int getPositionX();
	public int getPositionY();
	public EngineService getEngine();
	public HitboxService getcharBox();
	public int getLife();
	public int getSpeed();
	public boolean getFaceRight();
	public boolean isDead();
	//Operators: 
	public void moveLeft();
	public void moveRight();
	public void switchSide();
	public void step(CommandService c);
		//pre step() requires ¬dead

	//Observations: 
	/*
			[invariant]: 
				positionX(C) > 0 ∧ positionX(C) < Engine:: width(engine) positionY(C) > 0 ∧ positionY(C) < Engine:: height(engine) dead(C) = ¬(life > 0) 
			[init]: 
				life(init(l, s, f, e)) = l ∧ speed(init(l, s, f, e)) = s ∧ faceRight(init(l, s, f, e)) = f ∧engine(init(l, s, f, e)) = e 
			∃h :Hitbox, charbox(init(l, s, f, e)) = h 
			[moveLeft]: 
				(∃i, player(engine(C), i) 6= C ∧ collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = positionX(C)
				positionX(C) ≤ speed(C) ∧(∀i, player(engine(C), i) 6= C ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = positionX(C) − speed(C)
				positionX(C) > speed(C) ∧(∀i, player(engine(C), i) 6= C ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = 0 faceRight(moveLeft(C)) = faceRight(C) ∧ life(moveLeft(C)) = life(C) positionY(moveLeft(C)) = positionY(C) 
			[moveRight]: 
				...
			[switchSide]: 
				faceRight(switchSide(C))! = faceRight(C) positionX(switchSide(C)) = positionX(C) 
			[step]: 
				step(C, LEFT) = moveLeft(C) step(C, RIGHT) = moveRight(C) step(C, NEUTRAL) = C
	 */
}
