package ga.bitstring;

import ga.core.IIndividual;
import ga.bitstring.IBitCoding;
import ga.bitstring.TBitString;
import ga.realcode.TRealNumberIndividual;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * �r�b�g�X�g�����O�̌�
 * @since 2
 * @author isao
 */
public class TBitStringIndividual extends TRealNumberIndividual implements IIndividual, IBitCoding {

	/** ��ԁi���s�\�̂��ǂ����H�j */
	private int fStatus;
	
	/** �]���l */
	private double fEvaluationValue;
	
	/** �r�b�g�X�g�����O */
	private TBitString fBitString;	

	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TBitStringIndividual() {
		fStatus = IIndividual.INVALID;
		fEvaluationValue = Double.NaN;
		fBitString = new TBitString();
	}

	/**
	 * �R���X�g���N�^
	 * @param noOfBits �r�b�g��
	 * @since 2
	 */
	public TBitStringIndividual(int noOfBits) {
		fStatus = IIndividual.INVALID;
		fEvaluationValue = Double.NaN;
		fBitString = new TBitString(noOfBits);
	}

	/**
	 * �R���X�g���N�^
	 * @param src �R�s�[��
	 * @since 2
	 */
	public TBitStringIndividual(TBitStringIndividual src) {
		fStatus = src.fStatus;
		fEvaluationValue = src.fEvaluationValue;
		fBitString = new TBitString(src.fBitString);
	}

	/**
	 * @since 2
	 */
	@Override
	public Object clone() {
		return new TBitStringIndividual(this);		
	}

	/**
	 * �R�s�[
	 * @param src �R�s�[��
	 * @since 2
	 */
	public IIndividual copyFrom(IIndividual src) {
		TBitStringIndividual x = (TBitStringIndividual)src;
		fStatus = x.fStatus;
		fEvaluationValue = x.fEvaluationValue;
		fBitString.copyFrom(x.fBitString);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividual#readFrom(java.io.BufferedReader)
	 */
	public void readFrom(BufferedReader br) throws Exception {
		fStatus = Integer.parseInt(br.readLine());
		fEvaluationValue = Double.parseDouble(br.readLine());
		fBitString.readFrom(br);
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividual#writeTo(java.io.PrintWriter)
	 */
	public void writeTo(PrintWriter pw) {
		pw.println(fStatus);
		pw.println(fEvaluationValue);
		fBitString.writeTo(pw);
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividual#printOn()
	 */
	public void printOn() {
		System.out.println(fStatus);
		System.out.println(fEvaluationValue);
		fBitString.printOn();
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividual#getEvaluationValue()
	 */
	public double getEvaluationValue() {
		return fEvaluationValue;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividual#setEvaluationValue(double)
	 */
	public void setEvaluationValue(double value) {
		fEvaluationValue = value;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividual#getStatus()
	 */
	public int getStatus() {
		return fStatus;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IIndividual#setStatus(int)
	 */
	public void setStatus(int status) {
		fStatus = status;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.bitstring.IBitCoding#getBitString()
	 */
	public TBitString getBitString() {
		return fBitString;
	}

}
