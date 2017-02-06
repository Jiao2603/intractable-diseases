package ga.realcode;

import ga.core.IIndividual;
import ga.util.TComparator;
import ga.realcode.IRealNumberCoding;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * �����l�x�N�g���R�[�f�B���O�̌�
 * @since 2
 * @author yamhan
 * @author hmkz
 */
public class TRealNumberIndividual implements IIndividual, IRealNumberCoding {

	/** ��ԁi���s�\�̂��ǂ����H�j */
	private int fStatus;
	
	/** �]���l */
	private double fEvaluationValue;
	
	/** �����x�N�g�� */
	private TVector fRealVector;

	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TRealNumberIndividual() {
		this(0);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param dimension ������
	 * @since 2
	 */
	public TRealNumberIndividual(int dimension) {
		fStatus = INVALID;
		fEvaluationValue = -1.0;
		fRealVector = new TVector(dimension);
	}
	
	/**
	 * �R�s�[�R���X�g���N�^
	 * @param src �R�s�[��
	 * @since 62
	 */
	public TRealNumberIndividual(TRealNumberIndividual src) {
		fStatus = src.fStatus;
		fEvaluationValue = src.fEvaluationValue;
		fRealVector = new TVector(src.fRealVector);
	}

	/**
	 * @see IIndividual#clone()
	 * @since 2
	 */
	@Override
	public Object clone() {
		TRealNumberIndividual result = new TRealNumberIndividual(fRealVector.getDimension());
		result.copyFrom(this);
		return result;
	}

	/**
	 * @see IIndividual#copyFrom(IIndividual)
	 * @since 2
	 */
	public IIndividual copyFrom(IIndividual src) {
		TRealNumberIndividual ind = (TRealNumberIndividual) src;
		fEvaluationValue = ind.fEvaluationValue;
		fRealVector.copyFrom(ind.fRealVector);
		fStatus = ind.fStatus;
		return this;
	}

	/**
	 * @see IIndividual#readFrom(BufferedReader)
	 * @since 2
	 */
	public void readFrom(BufferedReader br) throws Exception {
		fStatus = Integer.parseInt(br.readLine().trim());
		fEvaluationValue = Double.parseDouble(br.readLine().trim());
		fRealVector.readFrom(br);
	}

	/**
	 * @see IIndividual#writeTo(PrintWriter)
	 * @since 2
	 */
	public void writeTo(PrintWriter pw) {
		pw.println(fStatus);
		pw.println(fEvaluationValue);
		fRealVector.writeTo(pw);
	}

	/**
	 * @see IIndividual#printOn()
	 * @since 2
	 */
	public void printOn() {
		PrintWriter pw = new PrintWriter(System.out, true);
		writeTo(pw);
	}

	/**
	 * @see IIndividual#getEvaluationValue()
	 * @since 2
	 */
	public double getEvaluationValue() {
		return fEvaluationValue;
	}

	/**
	 * @see IIndividual#setEvaluationValue(double)
	 * @since 2
	 */
	public void setEvaluationValue(double value) {
		fEvaluationValue = value;
	}

	/**
	 * @see IIndividual#getStatus()
	 * @since 2
	 */
	public int getStatus() {
		return fStatus;
	}

	/**
	 * @see IIndividual#setStatus(int)
	 * @since 2
	 */
	public void setStatus(int status) {
		fStatus = status;
	}

	/**
	 * �̂Ɋ܂܂��x�N�g����Ԃ��D
	 * @return �̂̃x�N�g��
	 * @since 2
	 */
	public TVector getVector() {
		return fRealVector;
	}

	/**
	 * �̂̂��]���l�C��ԁC�x�N�g�������ׂē������ꍇ��true��Ԃ��D�����łȂ����false�D
	 * �]���l�ƃx�N�g���̃`�F�b�N�̍ۂɂ͌덷�����e����D
	 * ���e�덷�̊m�F�E�ݒ�ɂ�{@link util.TComparator}�N���X���g���D
	 * @since 50
	 */
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof TRealNumberIndividual)) {
			return false;
		}
		TRealNumberIndividual ind = (TRealNumberIndividual) o;
		if (!TComparator.equals(fEvaluationValue, ind.fEvaluationValue)) {
			return false;
		}
		if (fStatus != ind.fStatus) {
			return false;
		}
		
		return fRealVector.equals(ind.fRealVector);
	}
}
