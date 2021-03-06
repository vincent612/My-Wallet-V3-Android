package piuk.blockchain.android.ui.addresses.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_accounts_row_header.view.*
import piuk.blockchain.android.R
import piuk.blockchain.android.ui.adapters.AdapterDelegate
import piuk.blockchain.android.util.gone
import piuk.blockchain.android.util.inflate
import piuk.blockchain.android.util.visible

class ImportedAccountsHeaderDelegate(
    val listener: AccountAdapter.Listener
) : AdapterDelegate<AccountListItem> {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        HeaderViewHolder(parent.inflate(R.layout.item_accounts_row_header))

    override fun onBindViewHolder(
        items: List<AccountListItem>,
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
        val headerViewHolder = holder as HeaderViewHolder
        headerViewHolder.bind(items[position] as AccountListItem.ImportedHeader, listener)
    }

    override fun isForViewType(items: List<AccountListItem>, position: Int): Boolean =
        items[position] is AccountListItem.ImportedHeader

    private class HeaderViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val header: TextView = itemView.header_name
        private val plus: ImageView = itemView.imageview_plus

        internal fun bind(item: AccountListItem.ImportedHeader, listener: AccountAdapter.Listener) {
            header.setText(R.string.imported_addresses)

            if (item.enableImport) {
                itemView.setOnClickListener { listener.onImportAddressClicked() }
                plus.visible()
            } else {
                itemView.setOnClickListener(null)
                plus.gone()
            }
            itemView.contentDescription = header.text
        }
    }
}