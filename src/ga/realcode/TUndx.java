package ga.realcode;

import ga.util.TComparator;
import ga.util.TMyRandom;

/**
 * �P�������K���z����(UNDX)
 * @since 2
 * @author isao
 */
public class TUndx {

	/** ���̃f�t�H���g�l */	
	public static double DEFAULT_ALPHA = 0.5;
	
	/** �x�[�^�̃f�t�H���g�l */
	public static double DEFAULT_BETA = 0.35;
	
	/** �V�X�e���p�����[�^ �� */
	private double fAlpha;
	
	/** �V�X�e���p�����[�^ �� */
	private double fBeta;
	
	/** �e�P�ւ̎Q�� */
	private ga.realcode.TVector fParent1;

	/** �e�Q�ւ̎Q�� */
	private ga.realcode.TVector fParent2;

	/** �e�R�ւ̎Q�� */
	private ga.realcode.TVector fParent3;

	/** �ЂP */
	private double fSigma1;
	
	/** �ЂQ */
	private double fSigma2;
	
	/** ���e�̎��̒P�ʃx�N�g�� e */
	private ga.realcode.TVector fEVector;
	
	/** �e�P�Ɛe�Q�̒��_�x�N�g�� */
	private ga.realcode.TVector fMean;
	
	/** �e1�Ɛe2�͓������H */
	private boolean isParent1EqualToParent2;
	
	/**
	 * �R���X�g���N�^
	 * @since 2
	 * @author isao
	 * @author hmkz ������TUndx(double, double)�ɈϏ�����
	 */
	public TUndx() {
		this(DEFAULT_ALPHA, DEFAULT_BETA);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param alpha �V�X�e���p�����[�^��
	 * @param beta �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 * @author hmkz ���������ɐ�������x�N�g����0�����Ƃ���
	 */
	public TUndx(double alpha, double beta) {
		fAlpha = alpha;
		fBeta = beta;
		fEVector = new ga.realcode.TVector();
		fMean = new ga.realcode.TVector();
	}
	
	/**
	 * �V�X�e���p�����[�^����ݒ肷��
	 * @param alpha �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public void setAlpha(double alpha) {
		fAlpha = alpha;
	}
	
	/**
	 * �V�X�e���p�����[�^����Ԃ��D
	 * @return �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public double getAlpha() {
		return fAlpha;
	}
	
	/**
	 * �V�X�e���p�����[�^����ݒ肷��D
	 * @param beta �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public void setBeta(double beta) {
		fBeta = beta;
	}
	
	/**
	 * �V�X�e���p�����[�^����Ԃ�
	 * @return �V�X�e���p�����[�^��
	 * @since 2
	 * @author isao
	 */
	public double getBeta() {
		return fBeta;
	}

	/**
	 * �e��ݒ肷��D
	 * @param p1 �e1�D�厲�Ɏg����
	 * @param p2 �e2�D�厲�Ɏg����
	 * @param p3 �e3�D�����Ɏg����
	 * @throws IllegalArgumentException �e�̎�������1�ł��قȂ�Ƃ�
	 * @since 2
	 * @author isao
	 * @author hmkz �ُ�l�ł���O���������Ȃ������o�O���C��
	 */
	public void setParents(ga.realcode.TVector p1, ga.realcode.TVector p2, ga.realcode.TVector p3) {
		fParent1 = p1;
		fParent2 = p2;
		fParent3 = p3;
		int dimension = fParent1.getDimension();
		if (dimension != fParent2.getDimension() || dimension != fParent3.getDimension()) {
			throw new IllegalArgumentException("The dimensions of parent 1, 2 and 3 are different.");
		}
		calcMean(fParent1, fParent2);
		calcUnitVectorAndStandardDeviationForPrimaryComponent(fParent1, fParent2);
		calcUnitVectorAndStandardDeviationForSecondaryComponent(fParent1, fParent3);
	}
	
	/**
	 * �q�𐶐�����D
	 * @param kid1 �q1�D���̃I�u�W�F�N�g�̒l���������ꂽ�q�̂��̂ɏ�����������D
	 * @param kid2 �q2�D���̃I�u�W�F�N�g�̒l���������ꂽ�q�̂��̂ɏ�����������D
	 * @since 2
	 * @author isao
	 */
	public void doIt(ga.realcode.TVector kid1, ga.realcode.TVector kid2) {
		int dimension = fParent1.getDimension();
		kid1.setDimension(dimension);
		kid2.setDimension(dimension);
		if (isParent1EqualToParent2) {
			kid1.copyFrom(fParent1);
			kid2.copyFrom(fParent2);
			return;
		}
		TMyRandom rand = TMyRandom.getInstance();
		// step.1
		// �x�N�g�� t �𐶐�
		ga.realcode.TVector t = new ga.realcode.TVector(dimension);
		for (int i = 0; i < dimension; i++) {
			t.setData(i, rand.getNormalDistributedNumber(0.0, fSigma2));
		}
		// step.2
		// t �� t - (t�Ee)e
		ga.realcode.TVector tmpVector = new ga.realcode.TVector(fEVector);
		tmpVector.scalarProduct(t.innerProduct(fEVector));
		t.sub(tmpVector);
		// step.3
		// t �� t + se
		tmpVector.copyFrom(fEVector);
		tmpVector.scalarProduct(rand.getNormalDistributedNumber(0.0, fSigma1));
		t.add(tmpVector);
		// step.4
		kid1.copyFrom(fMean);
		kid1.add(t);
		kid2.copyFrom(fMean);
		kid2.sub(t);
	}

	/**
	 * �e�P�Ɛe�Q�̒��_�����߂�D
	 * @param v1 �e�P
	 * @param v2 �e�Q
	 * @since 2
	 * @author isao
	 */
	private void calcMean(ga.realcode.TVector v1, ga.realcode.TVector v2) {
		fMean.copyFrom(v1);
		fMean.add(v2);
		fMean.scalarProduct(0.5);
	}

	/**
	 * �厲�i�e�P�Ɛe�Q�����Ԓ����j�����̒P�ʃx�N�g���ƕW���΍������߂�
	 * @param v1 �e�P
	 * @param v2 �e�Q
	 * @since 2
	 * @author isao
	 */
	private void calcUnitVectorAndStandardDeviationForPrimaryComponent(ga.realcode.TVector v1, ga.realcode.TVector v2) {
		fEVector.copyFrom(v2);
		fEVector.sub(v1);
		double d1 = fEVector.getLength(); // �e�P�Ɛe�Q�̊Ԃ̋���
		fSigma1 = fAlpha * d1; //�厲�����̕W���΍�
		if (TComparator.equals(d1, 0.0))
			isParent1EqualToParent2 = true;
		else
			isParent1EqualToParent2 = false;	
		fEVector.unitVector(); // �e�P�Ɛe�Q�����Ԓ����̕����P�ʃx�N�g��
	}
	
	/**
	 * ���������̕W���΍������߂�
	 * @param v1 �e�P
	 * @param v3 �e�R
	 * @since 2
	 * @author isao
	 */
	private void calcUnitVectorAndStandardDeviationForSecondaryComponent(ga.realcode.TVector v1, ga.realcode.TVector v3) {
		ga.realcode.TVector v1v3 = new ga.realcode.TVector(v3); // �e�P�Ɛe�R�����ԃx�N�g��
		v1v3.sub(v1);
		ga.realcode.TVector tmp = new ga.realcode.TVector(fEVector);
		tmp.scalarProduct(fEVector.innerProduct(v1v3));
		ga.realcode.TVector perpendicular = new ga.realcode.TVector(v1v3); // �e�R����e�P�Ɛe�Q�����Ԓ����։��낵�������x�N�g��
		perpendicular.sub(tmp);
		double d2 = perpendicular.getLength();
		fSigma2 = fBeta * d2 / Math.sqrt((double)v1.getDimension()); // ���������̕W���΍�		
	}
	
}
