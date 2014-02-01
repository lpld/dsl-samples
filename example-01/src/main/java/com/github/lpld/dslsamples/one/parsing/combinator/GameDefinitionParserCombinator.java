package com.github.lpld.dslsamples.one.parsing.combinator;

import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.lexing.Tokenizer;
import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.parsing.GameDefinitionParser;
import com.github.lpld.dslsamples.one.parsing.ParsingException;
import com.github.lpld.dslsamples.one.parsing.combinator.core.*;

/**
 * @author leopold
 * @since 1/4/14
 */
public class GameDefinitionParserCombinator implements GameDefinitionParser {

    private final String buffer;
    private final TokenBuffer tokenBuffer;

    private Combinator end = new TerminalParser(TokenType.END);
    private Combinator npc = new TerminalParser(TokenType.NPC);
    private Combinator npcs = new TerminalParser(TokenType.NPCS);
    private Combinator strength = new TerminalParser(TokenType.STRENGTH);
    private Combinator identifier = new TerminalParser(TokenType.IDENTIFIER);

    private Combinator npcDef = new SequenceCombinator(npc, strength, identifier, end);
    private Combinator npcList = new SequenceCombinator(npcs, new ListCombinator(npcDef), end);

    public GameDefinitionParserCombinator(String buffer) {
        if (buffer == null) {
            throw new NullPointerException();
        }

        this.buffer = buffer;
        this.tokenBuffer = new Tokenizer(buffer).getTokens();
    }

    @Override
    public Game parse() throws ParsingException {
        npcList.stepOver(new ParsingStep(true, tokenBuffer));
        return null;
    }
}
