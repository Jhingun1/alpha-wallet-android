package com.alphawallet.token.entity;

/**
 * Created by James on 28/05/2019.
 * Stormbird in Sydney
 */
public class TokenscriptElement
{
    public String ref;
    public String value;

    public boolean isToken()
    {
        return ref != null && ref.contains("tokenId");
    }

    public int getTokenIndex()
    {
        int index = -1;
        if (isToken())
        {
            try
            {
                String[] split = ref.split("[\\[\\]]");
                if (split.length == 2)
                {
                    String indexStr = split[1];
                    index = Integer.parseInt(indexStr);
                }
                else if (split.length == 1)
                {
                    index = 0;
                }
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }

        return index;
    }
}
