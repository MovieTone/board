function confirmEditing() {
	if (confirm("Предыдущая версия объявления будет изменена. Продолжить?")) {
		return true;
	}
	else {
		return false;
	}
}