package projetarm_v2.simulator.ui.javafx.ramview;

import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableListBase;
import projetarm_v2.simulator.core.Ram;

public class RamObservableListAdapter extends ModifiableObservableListBase<LineRam> {

	private Ram ram;
	private int offset;
	private OutputType outputType;
	private ShowType showType;
	
    public RamObservableListAdapter(Ram ram) {
		this.ram = ram;
		this.offset = 0;
		this.outputType = OutputType.NORMAL;
		this.showType = ShowType.BYTE;
	}

	@Override
    public LineRam get(int i) {
		int index;
		switch(showType) {
		default:
			case BYTE:
				index = i*4 + offset;
		        return new LineRamByte(this.ram.getByte(index),this.ram.getByte(index+1), this.ram.getByte(index+2), this.ram.getByte(index+3), outputType);
			case HALFWORD:
				index = i*10 + offset;
				return new LineRamHWord(this.ram.getHWord(index),this.ram.getHWord(index+2), this.ram.getHWord(index+4), this.ram.getHWord(index+8), outputType);
			case WORD:
				index = i*16 + offset;
				return new LineRamWord(this.ram.getValue(index),this.ram.getValue(index+4), this.ram.getValue(index+8), this.ram.getValue(index+12), outputType);
		}
    }

    @Override
    public int size() {
        return 10;
    }

	@Override
	protected void doAdd(int index, LineRam element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected LineRam doSet(int index, LineRam element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LineRam doRemove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public void setOutputType(OutputType type) {
		this.outputType = type;
	}
	
	public void setShowType(ShowType type) {
		this.showType = type;
	}
}