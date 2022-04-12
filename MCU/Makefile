include variables.mk

all:
	mkdir -p $(EXE_DIR)
	$(COMPILER) $(SOURCES) $(CC_OPTIONS) $(EXE_DIR)/$(FILE).hex	

upload:
	$(UPLOADER) $(U_OPTIONS)