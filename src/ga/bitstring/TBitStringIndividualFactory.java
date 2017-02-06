package ga.bitstring;

import ga.core.IIndividual;
import ga.core.IIndividualFactory;
import ga.bitstring.TBitStringIndividual;

/**
 * �r�b�g�X�g�����O�̂̃t�@�N�g��
 * @since 2
 * @author isao
 */
public class TBitStringIndividualFactory implements IIndividualFactory {
	
	/** �r�b�g�� */
	private int fNoOfBits;

	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TBitStringIndividualFactory() {
		fNoOfBits = -1;
	}
	
	/**
	 * �R���X�g���N�^
	 * @param noOfBits �r�b�g��
	 * @since 2
	 */
	public TBitStringIndividualFactory(int noOfBits) {
		fNoOfBits = noOfBits;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividualFactory#create()
	 */
	public IIndividual create() {
		if (fNoOfBits == -1)
			return new TBitStringIndividual();
		else
			return new TBitStringIndividual(fNoOfBits);
	}

}
