package ga.bitstring;

import ga.core.IIndividual;
import ga.core.IKidMaker;
import ga.bitstring.IBitCoding;
import ga.bitstring.TBitString;
import ga.bitstring.TUx;

/**
 * TUx�݂̂𗘗p�����q������
 * @since 2
 * @author isao
 */
public class TUxKidMaker implements IKidMaker {
	
	/** ��l���� */
	private TUx fUx;
	
	/** ��l�������K�v�Ƃ���e�̂̐� */
	private static final int NO_OF_PARENTS = 2;

	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TUxKidMaker() {
		fUx = new TUx();
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IKidMaker#getNoOfParents()
	 */
	public int getNoOfParents() {
		return NO_OF_PARENTS;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IKidMaker#setParents(ga.core.IIndividual[])
	 */
	public void setParents(IIndividual[] parents) {
		if (parents.length != NO_OF_PARENTS) {
			throw new IllegalArgumentException("parents.length != NO_OF_PARENTS");
		}
		for (int i = 0; i < parents.length; ++i) {
			if (!(parents[i] instanceof IBitCoding))
				throw new IllegalArgumentException("parents are not instance of IBitCoding");		
		}
		TBitString p1 = ((IBitCoding)parents[0]).getBitString();
		TBitString p2 = ((IBitCoding)parents[1]).getBitString();
		fUx.setParents(p1, p2);
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IKidMaker#doIt(int, ga.core.IIndividual[])
	 */
	public void doIt(int noOfKids, IIndividual[] kids) {
		if (kids.length < noOfKids) {
			throw new IllegalArgumentException("kids.length < noOfKids");
		}
		if (noOfKids % 2 != 0) {
			throw new IllegalArgumentException("noOfKids % 2 != 0");			
		}
		for (int i = 0; i < noOfKids / 2; ++i) {
			if (!(kids[2 * i] instanceof IBitCoding)) {
				throw new IllegalArgumentException("kids[" + 2 * i + "] is not instance of IBitCoding");
			}
			if (!(kids[2 * i + 1] instanceof IBitCoding)) {
				throw new IllegalArgumentException("kids[" + (2 * i + 1) + "] is not instance of IBitCoding");
			}
			TBitString kid1 = ((IBitCoding) kids[2 * i]).getBitString();
			TBitString kid2 = ((IBitCoding) kids[2 * i + 1]).getBitString();
			fUx.doIt(kid1, kid2);
		}
	}
	
}
