package com.alphawallet.app.ui.widget.entity;

import com.alphawallet.app.ui.widget.holder.TokenHolder;
import com.alphawallet.app.entity.tokens.Ticket;
import com.alphawallet.app.entity.tokens.Token;

public class TokenSortedItem extends SortedItem<Token> {

    public TokenSortedItem(Token value, int weight) {
        super(TokenHolder.VIEW_TYPE, value, weight);
    }

    @Override
    public int compare(SortedItem other) {
        return weight - other.weight;
    }

    @Override
    public boolean areContentsTheSame(SortedItem newItem) {
        if (viewType == newItem.viewType)
        {
            Token oldToken = value;
            Token newToken = (Token) newItem.value;
            if (!oldToken.getAddress().equals(newToken.getAddress())) return false;
            else if (weight != newItem.weight) return false;
            else if (!oldToken.getFullBalance().equals(newToken.getFullBalance())) return false;
            else if (!oldToken.pendingBalance.equals(newToken.pendingBalance)) return false;
            else if (!oldToken.getFullName().equals(newToken.getFullName())) return false;
            else if (oldToken.checkTickerChange(newToken)) return false;
            else if (oldToken.getInterfaceSpec() != newToken.getInterfaceSpec()) return false;

            //Had a redeem
            if (oldToken instanceof Ticket && newToken instanceof Ticket)
            {
                Ticket oTick = (Ticket) oldToken;
                Ticket nTick = (Ticket) newToken;

                return oTick.isMatchedInXML() == nTick.isMatchedInXML();
            }

            //TODO: balance value gone stale

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean areItemsTheSame(SortedItem other)
    {
        if (viewType == other.viewType)
        {
            Token oldToken = value;
            Token newToken = (Token) other.value;

            if (oldToken == null || newToken == null) return false;
            else return oldToken.getAddress().equals(newToken.getAddress()) && oldToken.tokenInfo.chainId == newToken.tokenInfo.chainId;
        }
        else
        {
            return false;
        }
    }
}
