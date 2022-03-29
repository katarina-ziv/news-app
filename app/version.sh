#!/bin/bash
git describe | awk -F "-" '{$(NF--)=""; print}' | sed -e 's/ /./'
