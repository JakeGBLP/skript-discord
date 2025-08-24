package it.jakegblp.skriptdiscord.api.jda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmbedType {
    IMAGE_EMBED(net.dv8tion.jda.api.entities.EmbedType.IMAGE),
    VIDEO_EMBED(net.dv8tion.jda.api.entities.EmbedType.VIDEO),
    GIF_EMBED(net.dv8tion.jda.api.entities.EmbedType.GIFV),
    ARTICLE_EMBED(net.dv8tion.jda.api.entities.EmbedType.ARTICLE),
    LINK_EMBED(net.dv8tion.jda.api.entities.EmbedType.LINK),
    RICH_EMBED(net.dv8tion.jda.api.entities.EmbedType.RICH),
    AUTO_MODERATION_EMBED(net.dv8tion.jda.api.entities.EmbedType.AUTO_MODERATION),
    POLL_RESULT_EMBED(net.dv8tion.jda.api.entities.EmbedType.POLL_RESULT);

    private final net.dv8tion.jda.api.entities.EmbedType embedType;
}
