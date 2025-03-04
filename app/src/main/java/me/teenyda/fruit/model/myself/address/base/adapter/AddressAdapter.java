package me.teenyda.fruit.model.myself.address.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Contact;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mContacts;
    private IContactAction mIContactAction;

    public interface IContactAction {
        void onContactSelect(Contact contact);
        void onContactDelete(int contactId);
    }

    public AddressAdapter(Context context) {
        mContext = context;
        mContacts = new ArrayList<>();
    }

    public void setContactAction(IContactAction contactAction) {
        this.mIContactAction = contactAction;
    }

    public void addContacts(List<Contact> contacts) {
        if (contacts != null && contacts.size() > 0) {
            mContacts.clear();
            mContacts.addAll(contacts);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.tv_name.setText(contact.getName());
        holder.address_mobile.setText(contact.getMobile());
        holder.address.setText(contact.getAddress());
        holder.contact_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIContactAction != null) {
                    mIContactAction.onContactDelete(contact.getContactId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView address_mobile;
        TextView address;
        ImageView contact_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            address_mobile = itemView.findViewById(R.id.address_mobile);
            address = itemView.findViewById(R.id.address);
            contact_delete = itemView.findViewById(R.id.contact_delete);
        }
    }
}
