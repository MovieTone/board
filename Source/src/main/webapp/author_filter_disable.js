function disableAuthor(checkbox_filter, author_filter) {
	if (checkbox_filter.checked) {
		author_filter.disabled = true;
	}
	else {
		author_filter.disabled = false;
	}
}