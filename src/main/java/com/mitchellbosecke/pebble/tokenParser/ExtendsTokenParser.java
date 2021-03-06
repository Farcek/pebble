/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.tokenParser;

import com.mitchellbosecke.pebble.error.ParserException;
import com.mitchellbosecke.pebble.lexer.Token;
import com.mitchellbosecke.pebble.lexer.TokenStream;
import com.mitchellbosecke.pebble.node.ExtendsNode;
import com.mitchellbosecke.pebble.node.RenderableNode;
import com.mitchellbosecke.pebble.node.expression.Expression;

public class ExtendsTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token) throws ParserException {
        TokenStream stream = this.parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip the 'extends' token
        stream.next();

        Expression<?> parentTemplateExpression = parser.getExpressionParser().parseExpression();

        stream.expect(Token.Type.EXECUTE_END);
        return new ExtendsNode(lineNumber, parentTemplateExpression);
    }

    @Override
    public String getTag() {
        return "extends";
    }
}
