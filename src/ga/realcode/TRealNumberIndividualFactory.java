package ga.realcode;

import ga.core.IIndividual;
import ga.core.IIndividualFactory;

/**
 * �̃t�@�N�g��
 * @since 2
 * @author yamhan, isao
 * @author hmkz
 */
public class TRealNumberIndividualFactory implements IIndividualFactory {
	
	private int fDimension = -1;
	
	/**
	 * �R���X�g���N�^
	 * @since 2
	 * @author yamhan, isao
	 */
	public TRealNumberIndividualFactory() {
	}

	/**
	 * �R���X�g���N�^
	 * @param dim ������
	 * @since 2
	 * @author yamhan, isao
	 */	
	public TRealNumberIndividualFactory(int dim) {
		fDimension = dim;
	}

	/**
	 * �̂𐶐�����D
	 * @return �������ꂽ��
	 * @since 2
	 * @author yamhan, isao
	 */
	public IIndividual create() {
		if ( fDimension <= 0 )
			return new TRealNumberIndividual();
		else
			return new TRealNumberIndividual(fDimension);	
	}

	/**
	 * ������������TRealNumberIndividual�𐶐�����Ȃ�true�C�����łȂ����false�D
	 * @since 55
	 * @author hmkz
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TRealNumberIndividualFactory)) {
			return false;
		}
		TRealNumberIndividualFactory f = (TRealNumberIndividualFactory) o;
		if (fDimension <= 0 && f.fDimension <= 0) {
			return true;
		} else {
			return fDimension == f.fDimension;
		}
	}
}
