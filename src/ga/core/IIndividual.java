package ga.core;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * �P�ړI�œK���p�̃C���^�[�t�F�[�X
 * @since 2
 * @author yamhan, isao
 */
public interface IIndividual {

	/** �̂��������Ă��邱�Ƃ�\���D */
	int VALID = 1;

	/** �̂����S���Ă��邱�Ƃ�\���D */
	int INVALID = 0;

	/**
	 * �������g�̕�����Ԃ��D
	 * @return �������g�̕���
	 * @since 2
	 */
	Object clone();

	/**
	 * src���������g�ɃR�s�[���āC���̌��ʂ�Ԃ��D
	 * @param src �R�s�[��
	 * @return �������g
	 * @since 2
	 */
	IIndividual copyFrom(IIndividual src);

	/**
	 * ���̓X�g���[������f�[�^��ǂݍ��ށD
	 * @param br ���̓X�g���[��
	 * @throws Exception
	 * @since 2
	 */
	void readFrom(BufferedReader br) throws Exception;

	/**
	 * �o�̓X�g���[���փf�[�^�������o���D
	 * @param pw �o�̓X�g���[��
	 * @since 2
	 */
	void writeTo(PrintWriter pw);

	/**
	 * �W���o�͂փf�[�^�������o���D
	 * @since 2
	 */
	void printOn();

	/**
	 * �]���l��Ԃ��D
	 * @return �]���l
	 * @since 2
	 */
	double getEvaluationValue();

	/**
	 * �]���l��ݒ肷��D
	 * @param value �]���l
	 * @since 2
	 */
	void setEvaluationValue(double value);

	/**
	 * �̂��������Ă��邩���S���Ă��邩�̒l��Ԃ��D 
	 * @return IIndividual.VALID:����, IIndividual.INVALID:���S
	 * @since 2
	 */
	int getStatus();	

	/**
	 * �̂��������Ă��邩���S���Ă��邩�̒l��ݒ肷��D
	 * @param status IIndividual.VALID:����, IIndividual.INVALID:���S
	 * @since 2
	 */
	void setStatus(int status);

}
