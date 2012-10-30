package helpers;

public interface GameObject{

	public enum ObjectType{
		ABILITY, CHARACTER, ITEM, SKILL, ACCOUNT
	}
	
	public GameObject.ObjectType getType();
}
