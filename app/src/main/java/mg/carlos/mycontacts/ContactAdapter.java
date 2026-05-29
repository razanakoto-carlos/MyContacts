package mg.carlos.mycontacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> contactList;

    public ContactAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact actuel = contactList.get(position);

        holder.textNom.setText(actuel.getNom());
        holder.textTelephone.setText(actuel.getTelephone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textNom;
        TextView textTelephone;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textNom = itemView.findViewById(R.id.textContactNom);
            textTelephone = itemView.findViewById(R.id.textContactTelephone);
        }
    }
}