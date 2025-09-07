package site.remlit.blueb.townyElections.model

import java.util.Locale
import java.util.Locale.getDefault

enum class Color {
	BLACK,
	DARK_BLUE,
	DARK_GREEN,
	CYAN,
	DARK_RED,
	DARK_PURPLE,
	GOLD,
	GRAY,
	DARK_GRAY,
	BLUE,
	LIME,
	AQUA,
	RED,
	PURPLE,
	YELLOW,
	WHITE;

	fun toLegacyCode() =
		when (this) {
			BLACK -> "&0"
			DARK_BLUE -> "&1"
			DARK_GREEN -> "&2"
			CYAN -> "&3"
			DARK_RED -> "&4"
			DARK_PURPLE -> "&5"
			GOLD -> "&6"
			GRAY -> "&7"
			DARK_GRAY -> "&8"
			BLUE -> "&9"
			LIME -> "&a"
			AQUA -> "&b"
			RED -> "&c"
			PURPLE -> "&d"
			YELLOW -> "&e"
			WHITE -> "&f"
		}

	companion object {
		fun fromString(s: String): Color = try {
			valueOf(s.uppercase(getDefault()))
		} catch (_: Exception) {
			throw Exception("Invalid color name.")
		}
	}
}