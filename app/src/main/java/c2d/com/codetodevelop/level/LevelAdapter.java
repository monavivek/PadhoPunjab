package c2d.com.codetodevelop.level;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import c2d.com.codetodevelop.R;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelViewHolder> {

    private List<Level> LevelList;

    public class LevelViewHolder extends RecyclerView.ViewHolder {
        public Button title;

        public LevelViewHolder(View view) {
            super(view);
            title = (Button) view.findViewById(R.id.level1);


        }
    }


    public LevelAdapter(List<Level> LevelList) {
        this.LevelList = LevelList;
    }

    @Override
    public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.level_list_row, parent, false);

        return new LevelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LevelViewHolder holder, int position) {

        Level level = LevelList.get(position);
        holder.title.setText(level.getTitle());

    }

    @Override
    public int getItemCount() {
        return LevelList.size();
    }
}
