package com.defano.hypertalk.ast.statement.command;

import com.defano.wyldcard.runtime.ExecutionContext;
import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.model.enums.SpeakingVoice;
import com.defano.hypertalk.ast.statement.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.wyldcard.sound.SpeechPlaybackManager;
import com.google.inject.Inject;
import org.antlr.v4.runtime.ParserRuleContext;

public class SpeakCmd extends Command {

    @Inject
    private SpeechPlaybackManager speechPlaybackManager;

    private final Expression textExpression;
    private final Expression voiceExpression;

    public SpeakCmd(ParserRuleContext context, Expression text) {
        this(context, text, null);
    }

    public SpeakCmd(ParserRuleContext context, Expression text, Expression voice) {
        super(context, "speak");
        this.textExpression = text;
        this.voiceExpression = voice;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException {
        SpeakingVoice voice = SpeakingVoice.getDefaultVoice();
        if (voiceExpression != null) {
            voice = SpeakingVoice.getVoiceByNameOrGender(voiceExpression.evaluate(context).toString());
        }

        String textToSpeak = textExpression.evaluate(context).toString();
        speechPlaybackManager.speak(textToSpeak, voice);
    }
}
