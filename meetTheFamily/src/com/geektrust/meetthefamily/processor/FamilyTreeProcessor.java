package com.geektrust.meetthefamily.processor;

import com.geektrust.meetthefamily.model.Family;
import com.geektrust.meetthefamily.utility.Constants;

/**
 * @author onikam
 */
public class FamilyTreeProcessor {

	/**
	 * 
	 * @param family
	 * @param command
	 * @return
	 */
	public String processCommand(Family family, String command) {
		String[] params = command.split(" ");
		String result = null;
		try {
			switch (params[0]) {

			case Constants.ADD_FAMILY_HEAD:
				result = family.addFamilyHead(params[1], params[2]);
				break;

			case Constants.ADD_SPOUSE:
				result = family.addSpouse(params[1], params[2], params[3]);
				break;

			case Constants.ADD_CHILD:
				result = family.addchild(params[1], params[2], params[3]);
				break;

			case Constants.GET_RELATIONSHIP:
				result = family.getRelationship(params[1], params[2]);
				break;

			default:
				result = Constants.INVALID_COMMAND;
				break;
			}
		} catch (Exception e) {
			result = Constants.INVALID_COMMAND;
		}
		return result;
	}
}
