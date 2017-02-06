package ga.bitstring;

import ga.core.IIndividual;
import ga.core.IIndividualFactory;
import ga.core.IInitialPopulationMaker;
import ga.core.TPopulation;
import ga.util.TMyRandom;
import ga.bitstring.IBitCoding;
import ga.bitstring.TBitString;
import ga.bitstring.TBitStringIndividualFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * �o�C�i���R�[�f�B���O�̂���\������鏉���W�c������
 * @since 2
 * @author isao
 */
public class TBitStringPopulationMaker implements IInitialPopulationMaker {
	
	/** �W�c�T�C�Y */
	private int fPopulationSize = 0;
	
	/** �r�b�g�� */
	private int fNoOfBits = 0;

	/** �̃t�@�N�g�� */
	private TBitStringIndividualFactory fIndividualFactory;
	
	/**
	 * �R���X�g���N�^
	 * @param populationSize �W�c�T�C�Y
	 * @param noOfBits �r�b�g��
	 * @since 2
	 */
	public TBitStringPopulationMaker(int populationSize, int noOfBits) {
		fPopulationSize = populationSize;
		fNoOfBits = noOfBits;
		fIndividualFactory = new TBitStringIndividualFactory(fNoOfBits);
	}
	
	/**
	 * �r�b�g����Ԃ��D
	 * @return �r�b�g��
	 * @since 2
	 */
	public int getNoOfBits() {
		return fNoOfBits;
	}

	/**
	 * �W�c�T�C�Y��Ԃ��D
	 * @return �W�c�T�C�Y
	 * @since 2
	 */
	public int getPopulationSize() {
		return fPopulationSize;
	}

	/**
	 * �̂������_���ɏ���������D
	 * @param ind ��
	 * @since 2
	 */
	private void initInitIndividual(IIndividual ind) {
		TBitString bs = ((IBitCoding)ind).getBitString();
		bs.setLength(fNoOfBits);
		TMyRandom rand = TMyRandom.getInstance();
		for(int i = 0; i < bs.getLength(); i++) {
			bs.setData(i, rand.getInteger(0, 1));		
		}
		ind.setStatus(IIndividual.INVALID);
	}
	
	/**
	 * �����W�c�𐶐�����D
	 * @since 2
	 */
	public TPopulation createInitialPopulation() {
		TPopulation result = new TPopulation(new TBitStringIndividualFactory(fNoOfBits), fPopulationSize);
		for (int i = 0; i < result.getSize(); ++i) {
			IIndividual ind = result.getIndividual(i);
			initInitIndividual(ind);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IInitialPopulationMaker#getIndividualFactory()
	 */
	public IIndividualFactory getIndividualFactory() {
		return fIndividualFactory;
	}
	
	/**
	 * �W�c���t�@�C���ɏo�͂���D
	 * @param pop �W�c
	 * @param filename �o�͐�̃t�@�C����
	 * @since 2
	 */
	private static void writePopulation(TPopulation pop, String filename) {
		try{
			BufferedWriter br1 = new BufferedWriter(new FileWriter(filename));
			PrintWriter pw = new PrintWriter(br1);
			pop.writeTo(pw);
			pw.close();
		} catch(Exception ex) {
		ex.printStackTrace();
		System.exit(5);
		}
	}

	/**
	 * ���C��
	 * @param args �W�c�T�C�Y ������ �P�ϐ�������̃r�b�g�� �����͈͂̍ŏ��l �ő�l �C���X�P�[����(true/false) �t�@�C���� �J�n�ԍ� �I���ԍ�
	 * @since 2
	 */
	public static void main(String[] args) {
		if(args.length != 5) {
			System.err.println("usage:java ga.TBitStringPopulationFactory popuationSize noOfBits filename startIndex endIndex");
			System.exit(-1);
		}
		int popSize = Integer.parseInt(args[0]);
		int noOfBits = Integer.parseInt(args[1]);
		String filename = args[2];
		int startIndex = Integer.parseInt(args[3]);
		int endIndex = Integer.parseInt(args[4]);
		TBitStringPopulationMaker initPopMaker = new TBitStringPopulationMaker(popSize, noOfBits);
		for(int i = startIndex; i <= endIndex; ++i) {
			String filenameWithIndex = filename + i;
			System.out.print("Creating " + filenameWithIndex + "...   ");
			TPopulation pop = initPopMaker.createInitialPopulation();
			writePopulation(pop, filenameWithIndex);
			System.out.println("done.");
		}
	}
	
}
