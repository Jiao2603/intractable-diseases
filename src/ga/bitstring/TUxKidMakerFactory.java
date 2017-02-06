package ga.bitstring;

import ga.core.IKidMaker;
import ga.core.IKidMakerFactory;
import ga.bitstring.TUxKidMaker;

/**
 * ��l�����t�@�N�g��
 * @since 2
 * @author isao
 */
public class TUxKidMakerFactory implements IKidMakerFactory {

	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TUxKidMakerFactory() {
	}

	/*
	 * (non-Javadoc)
	 * @see ga.core.IKidMakerFactory#create()
	 */
	public IKidMaker create() {
		return new TUxKidMaker();
	}

}
