package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.bold
import com.theonlytails.doddlebot.dodieYellow
import dev.minn.jda.ktx.Embed
import dev.minn.jda.ktx.messages.reply_
import java.awt.Color

val rules: CommandAction = {
    reply_(embed = Embed {
        dodieYellow()
        title = "Rules".bold()
        field("Please do", "Use the correct channels when sending messages, and think before @ing, or DMing a member")
        field("Don't be", "racist, homophobic, transphobic, antisemitic or generally disrespectful")
        field("Don't post", """NSFW (Not Safe For Work), NSFL (Not Safe For Life) and lewd content.
                |Advertising other discord server is strictly NOT allowed (Please DM a manager josh about server Advertising)
                |""".trimMargin()
        )
        field("Serious channel", """[This is an opt-in channel, use `/serious` for more info]
                |When using the serious channel please be aware that some members may find certain topics triggering, and remember that this chat is not a replacement for any kind of support you may have in place/need.
                |Always talk to your GP or therapist if you are experiencing issues with your mental health.
                |There is always someone to help you.""".trimMargin()
        )
        field("Allowed", """Self promotion is allowed in the related channels but do not spam.
                |Swearing is allowed so long as it is not too much or directed at someone with the intention of causing offense/upset.
                |""".trimMargin()
        )
    }).queue()
}
