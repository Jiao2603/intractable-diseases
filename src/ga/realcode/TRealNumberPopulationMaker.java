package ga.realcode;

import ga.core.IIndividual;
import ga.core.IIndividualFactory;
import ga.core.IInitialPopulationMaker;
import ga.core.TPopulation;
import ga.util.TMyRandom;
import ga.realcode.IRealNumberCoding;
import ga.realcode.TRealNumberIndividualFactory;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * �����x�N�g���̂���\������鏉���W�c������D
 * �������C�������̒�`��́C[0.0, 1.0]�ł���D
 * @since 2
 * @author isao
 */
public class TRealNumberPopulationMaker implements IInitialPopulationMaker {

	/** �W�c�T�C�Y */
	private int fPopulationSize = 0;

	/** ������ */
	private int fDimension = 0;
	
	/** �̃t�@�N�g�� */
	private IIndividualFactory fIndividualFactory = null;
	
	/** ��`��̍ŏ��l */
	public static final double MIN = 0.0;
	
	/** ��`��̍ő�l */
	public static final double MAX = 1.0;
	
	/**
	 * �R���X�g���N�^
	 * @since 2
	 * @author isao
	 * @author hmkz �������Ϗ�����
	 */
	public TRealNumberPopulationMaker(int popSize, int dim) {
		fPopulationSize = popSize;
		fDimension = dim;
		fIndividualFactory = new TRealNumberIndividualFactory();
	}
	
	/**
	 * �R�s�[�R���X�g���N�^
	 * @param src �R�s�[��
	 * @since 2
	 * @author isao
	 * @author hmkz �̃t�@�N�g���Ɩ��̃R�s�[������ǉ�
	 */
	public TRealNumberPopulationMaker(TRealNumberPopulationMaker src) {
		fPopulationSize = src.fPopulationSize;
		fIndividualFactory = src.fIndividualFactory;
		fDimension = src.fDimension;
	}
	
	/**
	 * �R�s�[
	 * @param src �R�s�[��
	 * @return �R�s�[����
	 * @since 2
	 * @author isao
	 * @author hmkz �̃t�@�N�g���Ɩ��̃R�s�[������ǉ�
	 */
	public TRealNumberPopulationMaker copyFrom(TRealNumberPopulationMaker src) {
		fPopulationSize = src.fPopulationSize;
		fIndividualFactory = src.fIndividualFactory;
		fDimension = src.fDimension;
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ga.IInitialPopulationMaker#getIndividualFactory()
	 */
	public IIndividualFactory getIndividualFactory() {
		return fIndividualFactory;
	}
	
	/**
	 * �̂̃����_�����������s���D
	 * @param ind ��
	 * @since 2
	 * @author isao
	 * @author hmkz �m���Ɏ��s�\������邽�߁C�̐����̌�ɕ]�����s���悤�ɕύX����
	 */
	private void initInitIndividual(IIndividual ind) {
		TMyRandom rand = TMyRandom.getInstance();
		TVector v = ((IRealNumberCoding)ind).getVector();
		v.setDimension(fDimension);
		for(int i = 0; i < v.getDimension(); i++) {
			v.setData(i, rand.getDouble(MIN, MAX));		
		}
	}
	
	/**
	 * �����_���ȏW�c�𐶐�����D
	 * ��肪�ݒ肳��Ă���ꍇ�C���s�\���݂̂���Ȃ�W�c�����������D
	 * @return �W�c
	 * @since 2
	 * @author isao
	 * @author hmkz ��肪�ݒ肳��Ă���ꍇ�C�K�����s�\���𐶐�����悤�ɂ����D
	 */
	public TPopulation createInitialPopulation() {
		TPopulation result = new TPopulation(fIndividualFactory, fPopulationSize);
		for (int i = 0; i < result.getSize(); ++i) {
			IIndividual ind = result.getIndividual(i);
			initInitIndividual(ind);
		}
		return result;
	}
	
	/**
	 * �W�c�T�C�Y��Ԃ��D
	 * @return �W�c�T�C�Y
	 * @since 2
	 * @author isao
	 */
	public int getPopulationSize() {
		return fPopulationSize;
	}

	/**
	 * �W�c�T�C�Y��ݒ肷��D
	 * @param populationSize �W�c�T�C�Y
	 * @since 2
	 * @author isao
	 */
	public void setPopulationSize(int populationSize) {
		fPopulationSize = populationSize;
	}

	/**
	 * ��������Ԃ��D
	 * @return ������
	 * @since 2
	 * @author isao
	 */
	public int getDimension() {
		return fDimension;
	}

	/**
	 * ��������ݒ肷��D
	 * @param dim ������
	 * @since 2
	 * @author isao
	 */
	public void setDimension(int dim) {
		fDimension = dim;
	}
	
	/**
	 * �W�c���t�@�C���֏o�͂���D
	 * @param pop �W�c
	 * @param filename �t�@�C����
	 * @since 2
	 * @author isao
	 */
	private static void writePopulation(TPopulation pop, String filename) {
		try{
			PrintWriter pw = new PrintWriter(new FileWriter(filename));
			pop.writeTo(pw);
			pw.close();
		} catch(Exception ex) {
		ex.printStackTrace();
		System.exit(5);
		}
	}

	/**
	 * ���C��
	 * @param args �W�c�T�C�Y ������ �t�@�C���� �J�n�ԍ� �I���ԍ�
	 * @since 2
	 * @author isao
	 */
	public static void main(String[] args) {
		if (args.length != 5) {
			System.err.println("usage:java funcOpt.TRealNumberPopulationMaker popuationSize Dimension filename startIndex endIndex");
			System.exit(-1);
		}
		int popSize = Integer.parseInt(args[0]);
		int dim = Integer.parseInt(args[1]);
		String filename = args[2];
		int startIndex = Integer.parseInt(args[3]);
		int endIndex = Integer.parseInt(args[4]);
		TRealNumberPopulationMaker initPopMaker = new TRealNumberPopulationMaker(popSize, dim);
		for(int i = startIndex; i <= endIndex; ++i) {
			String filenameWithIndex = filename + i;
			System.out.print("Creating " + filenameWithIndex + "...   ");
			TPopulation pop = initPopMaker.createInitialPopulation();
			writePopulation(pop, filenameWithIndex);
			System.out.println("done.");
		}
	}
	
}
