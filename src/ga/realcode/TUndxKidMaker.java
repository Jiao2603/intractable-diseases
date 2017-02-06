package ga.realcode;

import ga.core.IIndividual;
import ga.core.IKidMaker;
import ga.realcode.IRealNumberCoding;
import ga.realcode.TUndx;

/**
 * UNDX�ɂ��q������
 * @since 2
 * @author isao
 */
public class TUndxKidMaker implements IKidMaker {
	
	/** UNDX���K�v�Ƃ���e�̂̐� */
	private static final int NO_OF_PARENTS = 3;

	/** UNDX */
	private TUndx fUndx;
	
	/**
	 * �R���X�g���N�^
	 * �v���p�e�B�Ƃ��āCundx.alpha, undx.beta���K�v�D
	 * @since 2
	 * @author isao
	 * @author hmkz �����̓ǂݍ��݂��R�����g�A�E�g����Ă��������C��
	 */
	public TUndxKidMaker(double alpha, double beta) {
		fUndx = new TUndx(alpha, beta);
	}
	
	/**
	 * �V�X�e���p�����[�^����ݒ肷��
	 * @param alpha �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public void setAlpha(double alpha) {
		fUndx.setAlpha(alpha);
	}
	
	/**
	 * �V�X�e���p�����[�^����Ԃ��D
	 * @return �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public double getAlpha() {
		return fUndx.getAlpha();
	}
	
	/**
	 * �V�X�e���p�����[�^����ݒ肷��D
	 * @param beta �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public void setBeta(double beta) {
		fUndx.setBeta(beta);
	}
	
	/**
	 * �V�X�e���p�����[�^����Ԃ�
	 * @return �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public double getBeta() {
		return fUndx.getBeta();
	}

	/**
	 * @since 2
	 * @author isao
	 */
	public int getNoOfParents() {
		return NO_OF_PARENTS;
	}

	/**
	 * @see IKidMaker#setParents(IIndividual[])
	 * @since 2
	 * @author isao
	 */
	public void setParents(IIndividual[] parents) throws IllegalArgumentException {
		if (parents.length != NO_OF_PARENTS) {
			throw new IllegalArgumentException("parents.length != NO_OF_PARENTS");
		}
		if (!(parents[0] instanceof IRealNumberCoding)) {
			throw new IllegalArgumentException("parents[0] is not instance of IRealNumberCoding");
		}
		if (!(parents[1] instanceof IRealNumberCoding)) {
			throw new IllegalArgumentException("parents[1] is not instance of IRealNumberCoding");
		}
		if (!(parents[2] instanceof IRealNumberCoding)) {
			throw new IllegalArgumentException("parents[2] is not instance of IRealNumberCoding");
		}
		TVector v1 = ((IRealNumberCoding)parents[0]).getVector();
		TVector v2 = ((IRealNumberCoding)parents[1]).getVector();
		TVector v3 = ((IRealNumberCoding)parents[2]).getVector();
		fUndx.setParents(v1, v2, v3);
	}
	
	/**
	 * @see IKidMaker#doIt(int, IIndividual[])
	 * @since 2
	 * @author isao
	 */
	public void doIt(int noOfKids, IIndividual[] kids) throws IllegalArgumentException {
		if (kids.length < noOfKids) {
			throw new IllegalArgumentException("kids.length < noOfKids");
		}
		if (noOfKids % 2 != 0) {
			throw new IllegalArgumentException("noOfKids % 2 != 0");			
		}
		for (int i = 0; i < noOfKids / 2; ++i) {
			IRealNumberCoding kid1 = (IRealNumberCoding)kids[2 * i];
			IRealNumberCoding kid2 = (IRealNumberCoding)kids[2 * i + 1];
			TVector v1 = kid1.getVector();
			TVector v2 = kid2.getVector();
			fUndx.doIt(v1, v2);
		}
	}
	
}
