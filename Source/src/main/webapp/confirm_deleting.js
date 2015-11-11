function confirmDeleting() {
	if (confirm("Вы уверены, что хотите удалить это объявление?")) {
		return true;
	}
	else {
		return false;
	}
}