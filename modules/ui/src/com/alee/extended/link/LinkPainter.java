/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.extended.link;

import com.alee.painter.decoration.AbstractDecorationPainter;
import com.alee.painter.decoration.DecorationState;
import com.alee.painter.decoration.IDecoration;
import com.alee.utils.CompareUtils;

import java.util.List;

/**
 * Basic painter for {@link WebLink} component.
 * It is used as {@link WebLinkUI} default painter.
 *
 * @param <E> component type
 * @param <U> component UI type
 * @param <D> decoration type
 * @author Mikle Garin
 * @see <a href="https://github.com/mgarin/weblaf/wiki/How-to-use-WebLink">How to use WebLink</a>
 * @see WebLink
 */

public class LinkPainter<E extends WebLink, U extends WLinkUI, D extends IDecoration<E, D>>
        extends AbstractDecorationPainter<E, U, D> implements ILinkPainter<E, U>
{
    @Override
    protected void propertyChanged ( final String property, final Object oldValue, final Object newValue )
    {
        // Perform basic actions on property changes
        super.propertyChanged ( property, oldValue, newValue );

        // Updating visited state
        if ( CompareUtils.equals ( property, WebLink.VISITABLE_PROPERTY, WebLink.VISITED_PROPERTY ) )
        {
            if ( usesState ( DecorationState.visited ) )
            {
                updateDecorationState ();
            }
        }
    }

    @Override
    protected List<String> getDecorationStates ()
    {
        final List<String> states = super.getDecorationStates ();
        if ( component.isVisitable () )
        {
            states.add ( component.isVisited () ? DecorationState.visited : DecorationState.unvisited );
        }
        return states;
    }
}