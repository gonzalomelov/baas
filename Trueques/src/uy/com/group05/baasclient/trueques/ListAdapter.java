package uy.com.group05.baasclient.trueques;

import java.util.ArrayList;

import uy.com.group05.baasclient.sdk.entities.SimplePushChannelDTO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater lInflater;
	ArrayList<SimplePushChannelDTO> objects;
	ArrayList<Boolean> chequeados;

	ListAdapter(Context context, ArrayList<SimplePushChannelDTO> canales) {
		ctx = context;
		objects = canales;
		chequeados = new ArrayList<Boolean>();
		for (int i = 1; i <= canales.size(); i++) {
			chequeados.add(false);
		}
		
		lInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return objects.size();
	}

	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.item, parent, false);
		}

		SimplePushChannelDTO canal = getCanal(position);

		((TextView) view.findViewById(R.id.tvDescr)).setText(canal.getName());
		((TextView) view.findViewById(R.id.tvPrice)).setText("Descripción del canal");

		CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
		cbBuy.setOnCheckedChangeListener(myCheckChangList);
		cbBuy.setTag(position);
		//cbBuy.setChecked(canal.box);
		cbBuy.setChecked(chequeados.get(position));
		return view;
	}

	SimplePushChannelDTO getCanal(int position) {
		return ((SimplePushChannelDTO) getItem(position));
	}

	ArrayList<SimplePushChannelDTO> getBox() {
		ArrayList<SimplePushChannelDTO> box = new ArrayList<SimplePushChannelDTO>();
		int i = 0;
		for (SimplePushChannelDTO c : objects) {
			//if (c.box)
			if (chequeados.get(i))
				box.add(c);
			i++;
		}
		return box;
	}

	OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			chequeados.set((Integer) buttonView.getTag(), isChecked);
		}
	};
}